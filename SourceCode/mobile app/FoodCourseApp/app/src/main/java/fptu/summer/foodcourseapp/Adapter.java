package fptu.summer.foodcourseapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import fptu.summer.foodcourseapp.model.Product;
import fptu.summer.foodcourseapp.remote.APIUtils;
import fptu.summer.foodcourseapp.remote.RetrofitClient;
import retrofit2.Retrofit;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderProduct>{
    // la actiivity hien hanh
    private Context context;
    //data json
    List<Product> productList;
    List<Product> copyProductList;

    public Adapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
        copyProductList = new Vector<>(productList);
    }

    @NonNull
    @Override
    // mapping 1 product for litsize()
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_item,viewGroup,false);

        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct viewHolderProduct, int i) {
        //set data
        viewHolderProduct.setData(productList.get(i));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    void filer(String value){
        productList.clear();
        for (int i = 0 ; i < copyProductList.size();i++){
            if(copyProductList.get(i).getProName().toLowerCase().contains(value.toLowerCase())){
                productList.add(copyProductList.get(i));
            }
        }
        notifyDataSetChanged();
    }

    class ViewHolderProduct extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView txtProductName,txtStoreName,txtPrice,txtPriceDiscount;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtStoreName = itemView.findViewById(R.id.txtStoreName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtPriceDiscount = itemView.findViewById(R.id.txtPriceDiscount);
        }
        void setData(Product product){
            Glide.with(context).load(RetrofitClient.baseUrl+product.getProImage()).into(imageView);
            txtProductName.setText(productList.indexOf(product)+"."+product.getProName());
            txtStoreName.setText(product.getStoreName());

            txtPriceDiscount.setText(product.getPriceDiscount() + "");

            String htmlcontent = "<strike>" + product.getProPrice() +"</strike>";

            txtPrice.setText(android.text.Html.fromHtml(htmlcontent));

        }

    }
}
