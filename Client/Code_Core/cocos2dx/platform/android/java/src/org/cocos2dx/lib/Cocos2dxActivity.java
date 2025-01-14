/****************************************************************************
Copyright (c) 2010-2013 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 ****************************************************************************/
package org.cocos2dx.lib;

import java.io.File;

import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelperListener {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String TAG = Cocos2dxActivity.class.getSimpleName();

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Cocos2dxGLSurfaceView mGLSurfaceView;
	private Cocos2dxHandler mHandler;
	private static Context sContext = null;
	
	public boolean isInited = false;
	
	public static Context getContext() {
		return sContext;
	}
	
	public static void notifyBackup()
	{
		if(mBackupManager!=null)
			Log.d("Login","mBackupManager.dataChanged ");
			mBackupManager.dataChanged();
	}
	
	private static BackupManager mBackupManager;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
//		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//	             WindowManager.LayoutParams.FLAG_FULLSCREEN);
//	        this.getWindow().getDecorView()
//	            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//		if( ObbExpansionsManager.isMainFileExists(this.getApplicationContext()))
//		{
//			Cocos2dxHelper.setUsingObb(true);
//			ObbExpansionsManager.createNewInstance(this.getApplicationContext(),
//				new ObbExpansionsManager.ObbListener() {
//				
//				@Override
//				public void onMountSuccess() {
//					// TODO Auto-generated method stub
//					doInit();
//					isInited = true;
//					Cocos2dxHelper.onResume();
//					mGLSurfaceView.onResume();
//				}
//				
//				@Override
//				public void onFilesNotFound() {
//					// TODO Auto-generated method stub
//					Log.e(TAG, "obb not found....");
//				}
//			} );
//			
//		}
//		else
//		{
//			Cocos2dxHelper.setUsingObb(false);
		
//			doInit();
			
//		}
		
	}
	
	public boolean checkObb()
	{
		Cocos2dxHelper.checkObbFile(getApplicationContext());
		 
		File apk = new File(getApplicationInfo().sourceDir);
		if(!Cocos2dxHelper.isUsingObb() &&  apk.length() < 40*1024*1024)
		{
//		    AlertDialog.Builder bld = new AlertDialog.Builder(this);
//	        bld.setMessage("The Apk expansion data is missing,  please delete this App and install it from Google play again.("+
//	        		Cocos2dxHelper.getObbBasePath(getApplicationContext())
//	        		+")");
//	        bld.setNeutralButton("OK", new OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface arg0, int arg1) {
//					// TODO Auto-generated method stub
//					Cocos2dxHelper.terminateProcess();
//				}
//			});
//	        bld.create().show();
			return true;
		}
		
		return false;
	}
	
	public void doInit()
	{
		sContext = this;
    	this.mHandler = new Cocos2dxHandler(this);
    	 mBackupManager = new BackupManager(this);
    	 
    	this.init();

		Cocos2dxHelper.init(this, this);
		
		isInited = true;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onResume() {
		super.onResume();
		if(!isInited)
			return;
		Cocos2dxHelper.onResume();
		this.mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(!isInited)
			return;
		Cocos2dxHelper.onPause();
		this.mGLSurfaceView.onPause();
	}
	   @Override
	    public void onDestroy() {
		   super.onDestroy();
//		   if(ObbExpansionsManager.getInstance()!=null)
//		   {
//			   
//		   }
		
			
	   }
	
	@Override
	public void showDialog(final String pTitle, final String pMessage) {
		Message msg = new Message();
		msg.what = Cocos2dxHandler.HANDLER_SHOW_DIALOG;
		msg.obj = new Cocos2dxHandler.DialogMessage(pTitle, pMessage);
		this.mHandler.sendMessage(msg);
	}

	@Override
	public void showConfirmDialog(final String pTitle, final String pMessage, final String pButtonText, final OnClickListener callback) {
		Message msg = new Message();
		msg.what = Cocos2dxHandler.HANDLER_SHOW_CONFIRM_DIALOG;
		msg.obj = new Cocos2dxHandler.DialogMessage(pTitle, pMessage, pButtonText,callback);
		this.mHandler.sendMessage(msg);
	}
	
	@Override
	public void showEditTextDialog(final String pTitle, final String pContent, final int pInputMode, final int pInputFlag, final int pReturnType, final int pMaxLength) { 
		Message msg = new Message();
		msg.what = Cocos2dxHandler.HANDLER_SHOW_EDITBOX_DIALOG;
		msg.obj = new Cocos2dxHandler.EditBoxMessage(pTitle, pContent, pInputMode, pInputFlag, pReturnType, pMaxLength);
		this.mHandler.sendMessage(msg);
	}
	
	@Override
	public void runOnGLThread(final Runnable pRunnable) {
		this.mGLSurfaceView.queueEvent(pRunnable);
	}

    @Override
    protected void onStart() {
        super.onStart();
//        Cocos2dxHelper.gameHelper().onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!isInited)
			return;
        Cocos2dxHelper.gameHelper().onStop();
    }
    
    @Override
    protected void onActivityResult(int request, int response, Intent data) {
    	Log.i("Cocos2dxActivity", "onActivityResult");
        super.onActivityResult(request, response, data);
        Cocos2dxHelper.gameHelper().onActivityResult(request, response, data);
    }
    
	// ===========================================================
	// Methods
	// ===========================================================
	public void init() {
		
    	// FrameLayout
        ViewGroup.LayoutParams framelayout_params =
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                       ViewGroup.LayoutParams.FILL_PARENT);
        FrameLayout framelayout = new FrameLayout(this);
       
        framelayout.setLayoutParams(framelayout_params);

        // Cocos2dxEditText layout
        ViewGroup.LayoutParams edittext_layout_params =
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                       ViewGroup.LayoutParams.WRAP_CONTENT);
        Cocos2dxEditText edittext = new Cocos2dxEditText(this);
        edittext.setLayoutParams(edittext_layout_params);

        // ...add to FrameLayout
        framelayout.addView(edittext);

        // Cocos2dxGLSurfaceView
        this.mGLSurfaceView = this.onCreateView();
        
        // ...add to FrameLayout
        framelayout.addView(this.mGLSurfaceView);
      

        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(edittext);
//        if( ViewConfiguration.get(this).hasPermanentMenuKey())
//        {
//        	Log.i(TAG,"hasPermanentMenuKey");
//        }
       
        // Set framelayout as the content view
		setContentView(framelayout);
	}
	
    public Cocos2dxGLSurfaceView onCreateView() {
    	return new Cocos2dxGLSurfaceView(this,this.getWindowManager().getDefaultDisplay().getPixelFormat());
    }

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
