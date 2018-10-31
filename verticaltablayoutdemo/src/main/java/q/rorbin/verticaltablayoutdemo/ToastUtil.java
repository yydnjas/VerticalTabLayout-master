package q.rorbin.verticaltablayoutdemo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	private static Toast mToast = null;
	
	private static int ShowTime = 300;
	
	@SuppressLint("WrongConstant")
	private static void setCurrentToast(Context ctx, int contentId) {
		
		if (mToast != null) {
			mToast.setText(contentId);
		} else {
			mToast = Toast.makeText(ctx, contentId, ShowTime);
		}
	}
	
	@SuppressLint("WrongConstant")
	private static void setCurrentToast(Context ctx, String content) {
		if (mToast != null) {
			mToast.setText(content);
		} else {
			mToast = Toast.makeText(ctx, content, ShowTime);
		}
	}

	
	public static void showToast(Context context, int contentId) {
			setCurrentToast(context, contentId);
			mToast.show();
	}


	public static void showToast(Context context, String content) {
			setCurrentToast(context, content);
			mToast.show();
	}


	public static void cancelToast() {
		if (mToast != null)
			mToast.cancel();
	}
}
