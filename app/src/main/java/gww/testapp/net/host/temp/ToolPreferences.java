package gww.testapp.net.host.temp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.util.Set;

/**
 * desc: SharedPreferences工具类 <br/>
 * 特点：根据文件名称实例多个SP文件操作对象。<br/>
 * time: 2015-2-14 下午1:34:37 <br/>
 * author: 居廉 <br/>
 * since V 3.0.9 <br/>
 */
public class ToolPreferences {

    /**
     * 指定文件名称的名称的SharedPreferences对象
     */
    private SharedPreferences sp;

    /**
     * 指定文件名称的名称的编辑器对象
     */
    private Editor edit;

    @SuppressLint("CommitPrefEdits")
    private void init(Context context, String spFileName) {
        if (null == sp || edit == null) {
            sp = context.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
            edit = sp.edit();
        }
    }

    public String getString(Context context, String spFileName, String key, final String defaultValue) {
        init(context, spFileName);
        return sp.getString(key, defaultValue);
    }

    public void setString(Context context, String spFileName, final String key, final String value) {
        init(context, spFileName);
        edit.putString(key, value);
    }

    public boolean getBoolean(Context context, String spFileName, final String key, final boolean
            defaultValue) {
        init(context, spFileName);
        return sp.getBoolean(key, defaultValue);
    }

    public void setBoolean(Context context, String spFileName, final String key, final boolean value) {
        init(context, spFileName);
        edit.putBoolean(key, value);
    }

    public void setInt(Context context, String spFileName, final String key, final int value) {
        init(context, spFileName);
        edit.putInt(key, value);
    }

    public int getInt(Context context, String spFileName, final String key, final int defaultValue) {
        init(context, spFileName);
        return sp.getInt(key, defaultValue);
    }

    public void setFloat(Context context, String spFileName, final String key, final float value) {
        init(context, spFileName);
        edit.putFloat(key, value);
    }

    public float getFloat(Context context, String spFileName, final String key, final float defaultValue) {
        init(context, spFileName);
        return sp.getFloat(key, defaultValue);
    }

    public void setLong(Context context, String spFileName, final String key, final long value) {
        init(context, spFileName);
        edit.putLong(key, value);
    }

    public long getLong(Context context, String spFileName, final String key, final long defaultValue) {
        init(context, spFileName);
        return sp.getLong(key, defaultValue);
    }

    public void setStringSet(Context context, String spFileName, final String key, final Set<String> value) {
        init(context, spFileName);
        edit.putStringSet(key, value);
    }

    public Set<String> getStringSet(Context context, String spFileName, final String key, final Set<String>
            value) {
        init(context, spFileName);
        return sp.getStringSet(key, value);
    }

    /**
     * 是否有关键字
     *
     * @param context    上下文
     * @param spFileName SharedPreferences 文件名称
     * @param key        key名称
     * @return true:有
     */
    public boolean hasKey(Context context, String spFileName, final String key) {
        init(context, spFileName);
        return sp.contains(key);
    }

    /**
     * 提交修改(同步)
     */
    public void commit() {
        if (null != edit) {
            edit.commit();
        }
    }

    /**
     * 提交修改(异步)
     */
    public void apply() {
        if (null != edit) {
            edit.apply();
        }
    }

    /**
     * 删除keys`
     *
     * @param keys 删除的键值对
     */
    public void removeKeys(String... keys) {
        if (null != sp && keys != null && keys.length > 0) {
            final Editor editor = sp.edit();

            for (int i = 0; i < keys.length; i++) {
                if (!TextUtils.isEmpty(keys[i])) {
                    editor.remove(keys[i]);
                }
            }

            editor.apply();
        }
    }

    /**
     * 清除SharedPreferences所有数据
     *
     * @param context 上下文
     */
    public void clearPreference(Context context) {
        if (null != sp) {
            final Editor editor = sp.edit();
            editor.clear();
            editor.apply();
        }
    }

}
