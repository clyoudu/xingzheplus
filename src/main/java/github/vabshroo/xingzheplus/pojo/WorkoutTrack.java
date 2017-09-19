package github.vabshroo.xingzheplus.pojo;

import javax.persistence.*;

@Table(name = "workout_track")
public class WorkoutTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "track_no")
    private Long trackNo;

    @Column(name = "altitude")
    private double altitude;

    @Column(name = "gps_lat")
    private Double gpsLat;

    @Column(name = "gps_lon")
    private Double gpsLon;

    @Column(name = "gcj_lat")
    private Double gcjLat;

    @Column(name = "gcj_lon")
    private Double gcjLon;

    @Column(name = "bd_lat")
    private Double bdLat;

    @Column(name = "bd_lon")
    private Double bdLon;

    public Long getId() {
        return id;
    }

    public WorkoutTrack setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public WorkoutTrack setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }

    public Long getTrackNo() {
        return trackNo;
    }

    public WorkoutTrack setTrackNo(Long trackNo) {
        this.trackNo = trackNo;
        return this;
    }

    public double getAltitude() {
        return altitude;
    }

    public WorkoutTrack setAltitude(double altitude) {
        this.altitude = altitude;
        return this;
    }

    public Double getGpsLat() {
        return gpsLat;
    }

    public WorkoutTrack setGpsLat(Double gpsLat) {
        this.gpsLat = gpsLat;
        return this;
    }

    public Double getGpsLon() {
        return gpsLon;
    }

    public WorkoutTrack setGpsLon(Double gpsLon) {
        this.gpsLon = gpsLon;
        return this;
    }

    public Double getGcjLat() {
        return gcjLat;
    }

    public WorkoutTrack setGcjLat(Double gcjLat) {
        this.gcjLat = gcjLat;
        return this;
    }

    public Double getGcjLon() {
        return gcjLon;
    }

    public WorkoutTrack setGcjLon(Double gcjLon) {
        this.gcjLon = gcjLon;
        return this;
    }

    public Double getBdLat() {
        return bdLat;
    }

    public WorkoutTrack setBdLat(Double bdLat) {
        this.bdLat = bdLat;
        return this;
    }

    public Double getBdLon() {
        return bdLon;
    }

    public WorkoutTrack setBdLon(Double bdLon) {
        this.bdLon = bdLon;
        return this;
    }
}