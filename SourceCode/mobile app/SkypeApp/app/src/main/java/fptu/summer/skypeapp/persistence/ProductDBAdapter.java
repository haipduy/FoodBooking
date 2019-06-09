package fptu.summer.skypeapp.persistence;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Vector;

import fptu.summer.skypeapp.DetailProductActivity;
import fptu.summer.skypeapp.R;
import fptu.summer.skypeapp.model.Product;

import static fptu.summer.skypeapp.constants.RetrofitConstants.BASE_URL;


public class ProductDBAdapter extends RecyclerView.Adapter<ProductDBAdapter.ViewHolderProduct> {
    // la actiivity hien hanh
    private Context context;
    //data json
    List<Product> productList;
    List<Product> copyProductList;

    public ProductDBAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
        copyProductList = new Vector<>(productList);
    }

    @NonNull
    @Override
    // mapping 1 product for litsize()
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_item, viewGroup, false);

        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct viewHolderProduct, int i) {
        //set data
        viewHolderProduct.setData(productList.get(i));
    }

    public void filer(String value) {
        productList.clear();
        for (int i = 0; i < copyProductList.size(); i++) {
            if (copyProductList.get(i).getProName().toLowerCase().contains(value.toLowerCase())) {
                productList.add(copyProductList.get(i));
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        public static final String PRODUCT_CODE = "PRODUCT_CODE_MAIN";
        private ImageView imageView;
        private TextView txtProductName, txtStoreName, txtPrice, txtPriceDiscount;
        private LinearLayout container;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtStoreName = itemView.findViewById(R.id.txtStoreName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtPriceDiscount = itemView.findViewById(R.id.txtPriceDiscount);
            container = itemView.findViewById(R.id.container);

        }

        void setData(final Product product) {
            Glide.with(context).load(BASE_URL + product.getProImage()).into(imageView);

            txtProductName.setText(product.getProName());

            txtStoreName.setText(product.getStoreName());

            txtPriceDiscount.setText(product.getPriceDiscount() + "00");

            String htmlcontent = "<strike>" + product.getProPrice() + "00</strike>";

            txtPrice.setText(android.text.Html.fromHtml(htmlcontent));
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(PRODUCT_CODE,product);
                    context.startActivity(intent);
                }
            });

        }

    }
}
