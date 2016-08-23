package com.example.apple.imooczxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView mTvResult;
    private EditText mInput;
    private ImageView mIvResult;
    private CheckBox mLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mInput= (EditText) findViewById(R.id.et_text);
        mIvResult = (ImageView) findViewById(R.id.iv_resultbitmap);
        mLogo = (CheckBox) findViewById(R.id.cb_logo);
    }

    public void scan(View view) {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    public void make(View view) {
        String input = mInput.getText().toString();
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500,
                    mLogo.isChecked()? BitmapFactory.decodeResource(getResources(),R.drawable.icon_xian):null);
            mIvResult.setImageBitmap(bitmap);
        }
    }
    /**
     * 解析二维码
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTvResult.setText(result);
        }
    }
}
