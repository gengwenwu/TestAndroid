package gww.testapp.data.model;


import com.annimon.stream.Stream;

/**
 * desc: 演出 <br/>
 * time: 2018/4/19 下午1:38 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public interface Performance {

    String getName();

    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return Stream.concat(Stream.of(artist), artist.getMembers());
        });
    }

}
