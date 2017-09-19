package github.vabshroo.xingzheplus.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_workout")
public class UserWorkout {

    @Id
    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "userid")
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public UserWorkout setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public UserWorkout setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }
}