package ru.kastricyn.web3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor

@ManagedBean(name = "resultList", eager = true)
@SessionScoped
public class ResultList implements Serializable { // according to the specification, it does not require Serializable
    @ManagedProperty(value = "#{dataBase}")
    private DataBaseManager dataBaseManager;
    private Point canvasPoint = new Point();
    private List<Point> results = new ArrayList<>();

    @PostConstruct
    private void loadEntries() {
        results = dataBaseManager.loadEntries(FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));
    }

    public void addPoint(Point point) {
        try {
            dataBaseManager.addPointToDB(point);
        } catch (Exception e) {
            e.printStackTrace();
        }
        results.add(point);
        if (point == canvasPoint)
            canvasPoint = new Point();
    }

    @PreDestroy
    public void clearPoints() {
        try {
            for (Point p : results) {
                dataBaseManager.clearDB(p);
            }
            results.clear();
        } catch (SystemException | NotSupportedException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        results.clear();
    }
}
