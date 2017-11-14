package permission.android.library;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;


public class Permission {

    private WeakReference<Activity> context;
    /**
     * The Permissions.
     */
    protected ArrayList permissions;
    /**
     * The constant listener.
     */
    static OnRequestPermissionsBack listener;

    private Permission(Builder builder) {
        context = builder.context;
        permissions = new ArrayList<>(Arrays.asList(builder.permissions));
        listener = builder.listener;
        Intent callingIntent = PermissionActivity.getCallingIntent(context.get(), permissions,builder.requestId);
        context.get().startActivity(callingIntent);
    }


    /**
     * The interface On request permissions back.
     */
    public interface OnRequestPermissionsBack {
        /**
         * On request back.
         *
         * @param permissionResponse the permission response
         */
        void onRequestBack(int requestId,@NonNull PermissionResponse permissionResponse);
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private WeakReference<Activity> context;
        private String[] permissions;
        private int requestId;
        /**
         * The Listener.
         */
        protected OnRequestPermissionsBack listener;

        /**
         * Instantiates a new Builder.
         *
         * @param activity the activity
         */
        public Builder(Activity activity) {
            this.context = new WeakReference<>(activity);
        }


        /**
         * With permissions  . builder.
         *
         * @param permissions the permissions
         * @return the permission . builder
         */
        public Permission.Builder withPermissions(String... permissions) {
            this.permissions = permissions;
            return this;
        }

        /**
         * Sets listener.
         *
         * @param listener the listener
         * @return the listener
         */
        public Permission.Builder setListener(OnRequestPermissionsBack listener) {
            this.listener = listener;
            return this;
        }


        public Permission.Builder requestId(int requestId){
            this.requestId = requestId;
            return this;
        }

        /**
         * Check permission.
         *
         * @return the permission
         */
        public Permission check() {
            return new Permission(this);
        }
    }


}
