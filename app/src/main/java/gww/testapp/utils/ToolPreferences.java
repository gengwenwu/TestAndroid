package gww.testapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Set;

/**
 * desc: SharedPreferences工具类 <br/>
 * time: 2018/5/4 下午1:32 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */

public class ToolPreferences {

    /**
     * 指定文件名称的名称的SharedPreferences对象
     */
    private SharedPreferences mSharedPref;
    /**
     * 指定文件名称的名称的编辑器对象
     */
    private SharedPreferences.Editor mEdit;


    @SuppressLint("CommitPrefEdits")
    public ToolPreferences(Context context, String fileName, int mode) {
        mSharedPref = context.getSharedPreferences(fileName, mode);
        mEdit = mSharedPref.edit();
    }


    public String getString(String key, final String defaultValue) {
        return mSharedPref.getString(key, defaultValue);
    }

    public void setString(final String key, final String value) {
        mEdit.putString(key, value);
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        return mSharedPref.getBoolean(key, defaultValue);
    }

    public void setBoolean(final String key, final boolean value) {
        mEdit.putBoolean(key, value);
    }

    public void setInt(final String key, final int value) {
        mEdit.putInt(key, value);
    }

    public int getInt(final String key, final int defaultValue) {
        return mSharedPref.getInt(key, defaultValue);
    }

    public void setFloat(final String key, final float value) {
        mEdit.putFloat(key, value);
    }

    public float getFloat(final String key, final float defaultValue) {
        return mSharedPref.getFloat(key, defaultValue);
    }

    public void setLong(final String key, final long value) {
        mEdit.putLong(key, value);
    }

    public long getLong(final String key, final long defaultValue) {
        return mSharedPref.getLong(key, defaultValue);
    }

    public void setStringSet(final String key, final Set<String> value) {
        mEdit.putStringSet(key, value);
    }

    public Set<String> getStringSet(final String key, final Set<String> value) {
        return mSharedPref.getStringSet(key, value);
    }

    /**
     * 是否有关键字
     *
     * @param key key名称
     * @return true:有
     */
    public boolean hasKey(final String key) {
        return mSharedPref.contains(key);
    }

    /**
     * 提交修改(同步)
     */
    public void commit() {
        mEdit.commit();
    }

    /**
     * 提交修改(异步)
     */
    public void apply() {
        mEdit.apply();
    }

    /**
     * 删除keys
     *
     * @param keys 删除的键值对
     */
    public void removeKeys(String... keys) {
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                if (!TextUtils.isEmpty(key)) {
                    mEdit.remove(key);
                }
            }

            mEdit.apply();
        }
    }

    /**
     * 清除SharedPreferences所有数据
     */
    public void clearPreference() {
        mEdit.clear();
        mEdit.apply();
    }

    public SharedPreferences.Editor getEditor() {
        return mEdit;
    }

}
