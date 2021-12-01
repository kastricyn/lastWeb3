package ru.kastricyn.web3;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter @ToString
@NoArgsConstructor

@ManagedBean(name = "params", eager = true)
@ApplicationScoped
public class ParamsBean {

    private LocalDateTime dateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss");

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }


}
