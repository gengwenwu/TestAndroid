package gww.testapp.func;

/**
 * desc: <br/>
 * 测试 {@link android.support.annotation.IntDef} <br/>
 * 测试 {@link android.support.annotation.LongDef} <br/>
 * 测试 {@link android.support.annotation.StringDef} <br/>
 * time: 2018/8/16 上午10:18 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class Client {

    public void test() {
        setColor(RangeConst.RED);
        //setColor(100); //报错，值不存在


        setRank(RangeConst.Rank.HIGH);
        //setRank(1); //报错，1值是正确，但是还是爆红

        setBonusState(RangeConst.BonusState.USED);
        // setBonusState("used"); //报错，used值是正确，但是还是爆红
    }

    private void setColor(@RangeConst.Color int color) {

    }

    private void setRank(@RangeConst.Rank long rank) {

    }

    private void setBonusState(@RangeConst.BonusState String bonusState) {

    }

}
