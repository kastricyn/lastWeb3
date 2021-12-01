package ru.kastricyn.web3;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor

@ManagedBean(name = "point")
@RequestScoped

@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "sessionId", nullable = false)
    private String sessionId;
    @Column(name = "x", nullable = false)
    private double x;
    @Column(name = "y", nullable = false)
    private double y;
    @Column(name = "r", nullable = false)
    private double r;
    @Column(name = "entered", nullable = false)
    private boolean entered;

    public boolean check() {
        return (x >= 0 && x <= r) && (2 * y <= r && x / r + y / (x * r) <= 1) ||
                (x <= 0 && y <= 0) && x * x + y * y <= r*r / 4;
    }

    public boolean addResult(ResultList list) throws Exception {
        entered = check();
        list.addPoint(this);
        return entered;
    }

    @PostConstruct
    public void init() {
        sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
    }
}

