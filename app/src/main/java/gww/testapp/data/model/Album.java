package gww.testapp.data.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * desc: 专辑 <br/>
 * time: 2018/4/19 上午11:56 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public final class Album implements Performance {
    /**
     * 专辑名称
     */
    private String name;
    /**
     * 所有歌曲
     */
    private List<Track> tracks;
    /**
     * 所有艺术家
     */
    private List<Artist> musicians;


    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        this.name = name;
        this.tracks = new ArrayList<>(tracks);
        this.musicians = new ArrayList<>(musicians);
    }

    @Override
    public String getName() {
        return name;
    }

    public Stream<Track> getTracks() {
        return Stream.of(tracks);
    }

    public List<Track> getTrackList() {
        return Collections.unmodifiableList(tracks);
    }

    @Override
    public Stream<Artist> getMusicians() {
        return Stream.of(musicians);
    }

    public List<Artist> getMusicianList() {
        return Collections.unmodifiableList(musicians);
    }

    public Artist getMainMusician() {
        return musicians.get(0);
    }

    public Album copy() {
        List<Track> tracks = getTracks().map(Track::copy).collect(Collectors.toList());
        List<Artist> musicians = getMusicians().map(Artist::copy).collect(Collectors.toList());
        return new Album(name, tracks, musicians);
    }

}
