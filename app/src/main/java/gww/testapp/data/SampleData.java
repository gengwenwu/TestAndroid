package gww.testapp.data;

import com.annimon.stream.Stream;

import java.util.Arrays;
import java.util.List;

import gww.testapp.data.model.Album;
import gww.testapp.data.model.Artist;
import gww.testapp.data.model.Track;
import gww.testapp.ui.rxjava2.model.Car;
import gww.testapp.ui.rxjava2.model.User;

import static java.util.Arrays.asList;

/**
 * desc: 简单数据 <br/>
 * time: 2018/5/5 下午5:57 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public interface SampleData {

    String USER_ZHANG_SAN_JSON = "{\"age\":22,\"name\":\"张三\"}";
    String USER_LI_SI_JSON = "{\"age\":33,\"name\":\"李四\"}";


    Car CAR_AUDI_A3 = new Car("A3", "奥迪");
    Car CAR_AUDI_Q5 = new Car("Q5", "奥迪");
    Car CAR_BMW_X3 = new Car("X3", "宝马");
    Car CAR_BMW_X6 = new Car("X6", "宝马");

    User USER_ZHANG_SAN = new User("张三", 22, Arrays.asList(CAR_AUDI_A3, CAR_AUDI_Q5));
    User USER_LI_SI = new User("李四", 33, Arrays.asList(CAR_BMW_X3, CAR_BMW_X6));


    /* 歌手 */
    public static final Artist zhouJieLun = new Artist("周杰伦", "中国台湾");
    public static final Artist zhangXueYou = new Artist("张学友", "中国香港");
    public static final Artist liuDeHua = new Artist("刘德华", "中国香港");
    public static final Artist guoFuCheng = new Artist("郭富城", "中国香港");
    public static final Artist niMing = new Artist("黎明", "中国香港");
    public static final Artist laoLang = new Artist("老狼", "中国北京");

    /* 乐队 */
    public static final List<Artist> membersOfGod = Arrays.asList(zhangXueYou, liuDeHua, guoFuCheng, niMing);
    public static final Artist theGod = new Artist("四大天王", membersOfGod, "香港");

    // 所有歌手
    public static final List<Artist> allArtists = Arrays.asList(zhouJieLun, laoLang, theGod, zhangXueYou, liuDeHua, guoFuCheng, niMing);

    /* 专辑 */
    // 周杰伦 《叶惠美》
    public static final Album yeHuiMei = new Album("叶惠美", asList(new Track("东风破", 300), new Track("以父之名", 400), new Track("简单爱", 600)), asList(zhouJieLun));
    // 张学友 《吻别》
    public static final Album kissGoodBye = new Album("吻别", asList(new Track("一路上有你", 500), new Track("每天爱你多一些", 300)), asList(zhangXueYou));
    // 刘德华 《忘情水》
    public static final Album wangQinShui = new Album("忘情水"
            , asList(new Track("练习", 100), new Track("爱你一万年", 45), new Track("缠绵", 50), new Track("天意", 120), new Track("谢谢你的爱", 80))
            , asList(liuDeHua));
    public static final Album baDuKongJian = new Album("八度空间", asList(new Track("半兽人", 450), new Track("半岛铁盒", 400)), asList(zhouJieLun));

    // 滚石岁月经典
    public static final Album rollingStonesYears = new Album("滚石岁月经典"
            , asList(new Track("心太软", 500), new Track("彩虹", 300), new Track("童年", 400))
            , asList(new Artist("任贤齐", "中国香港"), new Artist("羽泉", "中国大陆"), new Artist("罗大佑", "中国台湾")));


    // 所有专辑
    public static final List<Album> allAlbums = Arrays.asList(yeHuiMei, kissGoodBye, wangQinShui, baDuKongJian);
    public static Stream<Album> albums = Stream.of(yeHuiMei);

    public static Stream<Artist> threeArtists() {
        return Stream.of(zhouJieLun, laoLang, theGod);
    }

    public static List<Artist> getThreeArtists() {
        return Arrays.asList(zhouJieLun, laoLang, theGod);
    }


}