package net.yury.core.action;

import net.yury.core.event.Event;
import net.yury.core.event.InsertEvent;
import net.yury.drivers.YB003Service;

import java.sql.SQLException;
import java.util.List;

public class YB003InsertAction extends Action {
    private YB003Service yb003Service;
    public YB003InsertAction() {
        this.yb003Service = new YB003Service();
    }
    @Override
    public void process(Event event) throws RuntimeException {
        InsertEvent e = (InsertEvent) event;
        try {
            YB003Service.insertYB003(e.getNewData(), e.getTableName());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void batchProcess(List<Event> eventList) throws RuntimeException {
        throw new RuntimeException("not supported");
    }
}
