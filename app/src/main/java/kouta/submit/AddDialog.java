package kouta.submit;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddDialog extends Dialog{

    public Button cancel;
    public Button ok;
    public EditText editText;

    public AddDialog(Context mContext, String mTitle, String mSubtitle, String mOk, String mCancel, String mHint){
        super(mContext);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.add_dialog);

        cancel = findViewById(R.id.cancel);
        ok = findViewById(R.id.ok);
        editText = findViewById(R.id.editText);
        TextView title = findViewById(R.id.title);
        TextView subtitle = findViewById(R.id.subtitle);

        title.setText(mTitle);
        subtitle.setText(mSubtitle);
        ok.setText(mOk);
        cancel.setText(mCancel);
        editText.setHint(mHint);
    }
}
