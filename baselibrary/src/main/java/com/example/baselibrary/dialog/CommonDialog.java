package com.example.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import com.example.baselibrary.R;

/**
 * 万能的dialog
 * Created by cherish
 */

public class CommonDialog extends Dialog {
    private AlertCommonController mAlert;
    public CommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertCommonController(this, getWindow());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }



    public  static  class Builder{
        private final AlertCommonController.AlertParams P;


        /**
         * Creates a builder for an alert dialog that uses an explicit theme
         * resource.
         * <p>
         * The specified theme resource ({@code themeResId}) is applied on top
         * of the parent {@code context}'s theme. It may be specified as a
         * style resource containing a fully-populated theme, such as
         * {@link android.R.style#Theme_Material_Dialog}, to replace all
         * attributes in the parent {@code context}'s theme including primary
         * and accent colors.
         * <p>
         * To preserve attributes such as primary and accent colors, the
         * {@code themeResId} may instead be specified as an overlay theme such
         * as {@link android.R.style#ThemeOverlay_Material_Dialog}. This will
         * override only the window attributes necessary to style the alert
         * window as a dialog.
         * <p>
         * Alternatively, the {@code themeResId} may be specified as {@code 0}
         * to use the parent {@code context}'s resolved value for
         * {@link android.R.attr#alertDialogTheme}.
         *
         * @param context the parent context

         */
        public Builder(Context context) {
            P = new AlertCommonController.AlertParams(context);
        }

        /**
         * Returns a {@link Context} with the appropriate theme for dialogs created by this Builder.
         * Applications should use this Context for obtaining LayoutInflaters for inflating views
         * that will be used in the resulting dialogs, as it will cause views to be inflated with
         * the correct theme.
         *
         * @return A Context for built Dialogs.
         */
        public Context getContext() {
            return P.mContext;
        }

        /**
         * Set the title using cache by spaceArray
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public CommonDialog.Builder setTitle(int viewId,CharSequence text) {
            //缓存
            P.textArray.put(viewId, text);
            return this;
        }
        /**
         *Set the clickListener
         */
        public  CommonDialog.Builder setOnclickListener(int viewId, View.OnClickListener listener){
            P.clickArray.put(viewId,  listener);
            return this;
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public CommonDialog.Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * Sets the callback that will be called if the dialog is canceled.
         *
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(android.content.DialogInterface.OnDismissListener) setOnDismissListener}.</p>
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public CommonDialog.Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.setOnCancelListener(onCancelListener);
            return this;
        }

        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public CommonDialog.Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.setOnDismissListener(onDismissListener);
            return this;
        }

        /**
         * Sets the callback that will be called if a key is dispatched to the dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public CommonDialog.Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.setOnKeyListener(onKeyListener);
            return this;
        }
        /**
         * Set a custom view resource to be the contents of the Dialog. The
         * resource will be inflated, adding all top-level views to the screen.
         *
         * @param layoutResId Resource ID to be inflated.
         * @return this Builder object to allow for chaining of calls to set
         *         methods
         */
        public CommonDialog.Builder setView(int layoutResId) {
            P.mView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }
        /**
         * Sets a custom view to be the contents of the alert dialog.
         * <p>
         * When using a pre-Holo theme, if the supplied view is an instance of
         * <p>
         * <strong>Note:</strong> To ensure consistent styling, the custom view
         * should be inflated or constructed using the alert dialog's themed
         * context obtained via {@link #getContext()}.
         *
         * @param view the view to use as the contents of the alert dialog
         * @return this Builder object to allow for chaining of calls to set
         *         methods
         */
        public CommonDialog.Builder setView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        /**
         * set fullScreen
         * @param fullScreen
         * @return
         */
        public CommonDialog.Builder  setFullScreen(boolean fullScreen) {
            P.mFullScreen=fullScreen;
            return this;
        }
        public CommonDialog.Builder  setGravity(int gravity) {
            P.mGravity=gravity;
            return this;
        }
        public CommonDialog.Builder  addAnimal(int animals) {
            P.mAnimations=animals;
            return this;
        }
        /**
         * Creates an {@link CommonDialog} with the arguments supplied to this
         * builder.
         * <p>
         * Calling this method does not display the dialog. If no additional
         * processing is needed, {@link #show()} may be called instead to both
         * create and display the dialog.
         */
        public CommonDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final CommonDialog dialog = new CommonDialog(P.mContext, R.style.dialog);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * Creates an {@link CommonDialog} with the arguments supplied to this
         * builder and immediately displays the dialog.
         * <p>
         * Calling this method is functionally identical to:
         * <pre>
         *     CommonDialog dialog = builder.create();
         *     dialog.show();
         * </pre>
         */
        public CommonDialog show() {
            final CommonDialog dialog = create();
            dialog.show();
            return dialog;
        }



    }

}
