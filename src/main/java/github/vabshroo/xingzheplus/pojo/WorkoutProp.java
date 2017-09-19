package github.vabshroo.xingzheplus.pojo;

import javax.persistence.*;

@Table(name = "workout_prop")
public class WorkoutProp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "prop_name")
    private String propName;

    @Column(name = "prop_value")
    private String propValue;

    public Long getId() {
        return id;
    }

    public WorkoutProp setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public WorkoutProp setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
        return this;
    }

    public String getPropName() {
        return propName;
    }

    public WorkoutProp setPropName(String propName) {
        this.propName = propName;
        return this;
    }

    public String getPropValue() {
        return propValue;
    }

    public WorkoutProp setPropValue(String propValue) {
        this.propValue = propValue;
        return this;
    }
}