package gww.testapp.func;

import android.support.annotation.IntDef;
import android.support.annotation.LongDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * desc: 常量范围 <br/>
 * time: 2018/8/16 上午10:30 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public interface RangeConst {

    /**
     * 演示 {@link IntDef}特性
     */
    int RED = 1;
    int BLUE = 2;
    int YELLOW = 3;

    @IntDef({RED, BLUE, YELLOW})
    @Retention(RetentionPolicy.SOURCE)
    @interface Color {

    }

    /**
     * 演示 {@link LongDef}
     */
    @LongDef({Rank.HIGH, Rank.NORMal, Rank.LOW})
    @Retention(RetentionPolicy.SOURCE)
    @interface Rank {
        long HIGH = 1;
        long NORMal = 2;
        long LOW = 3;
    }

    /**
     * 演示 {@link }
     */
    @StringDef({BonusState.UNUSED, BonusState.USED, BonusState.LAPSED})
    @Retention(RetentionPolicy.SOURCE)
    @interface BonusState {
        String UNUSED = "unused";
        String USED = "used";
        String LAPSED = "lapsed";
    }

}
