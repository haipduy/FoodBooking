package fptu.summer.skypeapp.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;
import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.entity.BankAccount;
import fptu.summer.skypeapp.presenter.CamViewPresenter;
import fptu.summer.skypeapp.utils.BundleString;

import static fptu.summer.skypeapp.utils.BundleString.SCAN_100000;
import static fptu.summer.skypeapp.utils.BundleString.SCAN_200000;
import static fptu.summer.skypeapp.utils.BundleString.SCAN_50000;
import static fptu.summer.skypeapp.utils.BundleString.SCAN_500000;

public class CamViewActivity extends AppCompatActivity {

    private ScannerLiveView mCamera;
    private TextView txtAmountQRcode;
    private Button btnNapTien;
    private CamViewPresenter camViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_view);
        initialView();
        initialData();
    }

    private void initialView() {
        mCamera = findViewById(R.id.camView);
        camViewPresenter = new CamViewPresenter(this);
    }

    private void initialData() {
        shouldAskPermission();
        mCamera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {

            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {

            }

            @Override
            public void onScannerError(Throwable err) {
                Log.e("Scanner Error: ", err.getMessage());
            }

            @Override
            public void onCodeScanned(String data) {

                scanQRCode(data);

                if (mCamera != null) {
                    if (mCamera.getCamera() != null && mCamera.getCamera().getController() != null) {
                        mCamera.getCamera().getController().switchFlashlight(false);
                    }
                    mCamera.stopScanner();
                }
            }
        });
    }

    private void shouldAskPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    100);
        } else {
            startScanner();
        }
    }

    private void startScanner() {
        ZXDecoder decoder = new ZXDecoder();
        decoder.setScanAreaPercent(0.5);
        mCamera.setDecoder(decoder);
        mCamera.startScanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            if (mCamera.getCamera() != null && mCamera.getCamera().getController() != null) {
                mCamera.getCamera().getController().switchFlashlight(false);
            }
            mCamera.stopScanner();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startScanner();
                }
                break;
        }
    }

    private void scanQRCode(String data) {

        float amount = 0;
        boolean result = false;

        switch (data) {
            case SCAN_50000:
                amount = 50000;
                break;
            case SCAN_100000:
                amount = 100000;
                break;
            case SCAN_200000:
                amount = 200000;
                break;
            case SCAN_500000:
                amount = 500000;
                break;

        }
        if (amount != 0) {
            BankAccount bankAccount = MainActivity.bankAccount;
            int bankId = bankAccount.getBankId();
            camViewPresenter.updateMoneyBank(bankId, amount);

        } else {
            String msg="QR Code không phù hợp, vui lòng thử lại!";
            showDialogQRCode(msg);

        }
        Intent intent = new Intent(CamViewActivity.this, ProfileActivity.class);
        startActivity(intent);

    }

    public void showDialogQRCode(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
