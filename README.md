# Permission

**Gradle**

```gradle

dependencies {
	compile 'permission.android.chinnadurai'
}
```

------ 
# Usage


After adding the library, you just need to create an instance from Gota libary and passing an array of permissions.

```java  
new Permission.Builder( this )
                       .withPermissions( Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS )
                       .requestId( 1 )
                       .setListener( this )
                       .check();
```
### `OnRequestPermissionsBack`
In order to receive the response, you will need to implement the `OnRequestPermissionsBack`  interfaces.

```java
   @Override
     public void onRequestBack(int requestId, @NonNull PermissionResponse permissionResponse) {
         if (permissionResponse.isGranted( Manifest.permission.CAMERA )) {
                   
                }
    } 
```

### `GotaResponse` methods
* `deniedPermissions()` 
	* Return a list of denied permissions.
* `grantedPermissions()`
	* Return a list of grated permissions.
* `isGranted(String)`
	* To check if the permission was granted or not.   
*  `isDenied(String)`
	* To check if the permission was denied or not.   
* `isAllGranted()`
	* return true if the all permission was grated
* `isAllDenied()`
	* return true if the all permission was denied
* `hasDeniedPermission()`
	* return true if there's any denied permission
* `isOnNeverAskAgain()`
	* return true if there's any permission that checked as never ask for permission again.
* `requestId` 
	* Id or token that was submited with Permission request.
 
