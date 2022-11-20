package net.yury.drivers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class YB003Service extends PGService {
    private static final Logger LOG = LoggerFactory.getLogger(YB003Service.class);
    public static final String yb003SQL = "insert into ? values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where not exists( select 1 from yb003 where seq = ?)";
    public static void insertYB003(ObjectNode data, String tableName) throws SQLException {
        Connection connection = PG.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(yb003SQL);
            statement.setString(1, tableName);
            statement.setLong(2, data.get("seq").longValue());
            statement.setLong(3, data.get("ctime").longValue());
            statement.setLong(4, data.get("mtime").longValue());
            statement.setLong(5, data.get("rtime").longValue());
            statement.setBigDecimal(6, data.get("isvalid").decimalValue());
            statement.setString(7, data.get("remark").textValue());
            statement.setString(8, data.get("orgid_yb003").textValue());
            statement.setString(9, data.get("humanid_yb003").textValue());
            statement.setInt(10, data.get("declaredate").intValue());
            statement.setString(11, data.get("title").textValue());
            statement.setString(12, data.get("f001v_yb003").textValue());
            statement.setString(13, data.get("f002v_yb003").textValue());
            statement.setString(14, data.get("f003v_yb003").textValue());
            statement.setString(15, data.get("datasource").textValue());
            statement.setString(16, data.get("modifysource").textValue());
            statement.setString(17, data.get("f004v_yb003").textValue());
            statement.setString(18, data.get("f005v_yb003").textValue());
            statement.setString(19, data.get("f006v_yb003").textValue());
            statement.setString(20, data.get("f007v_yb003").textValue());
            statement.setBigDecimal(21, data.get("f008n_yb003").decimalValue());
            statement.setBigDecimal(22, data.get("f009n_yb003").decimalValue());
            statement.setString(23, data.get("f010v_yb003").textValue());
            statement.setString(24, data.get("f011v_yb003").textValue());
            statement.setString(25, data.get("f012v_yb003").textValue());
            statement.setString(26, data.get("f013v_yb003").textValue());
            statement.setBigDecimal(27, data.get("f014n_yb003").decimalValue());
            statement.setBigDecimal(28, data.get("f015n_yb003").decimalValue());
            statement.setString(29, data.get("url_yb003").textValue());
            statement.setBigDecimal(30, data.get("ischeck").decimalValue());
            statement.setDouble(31, data.get("f016n_yb003").asDouble());
            statement.setDouble(32, data.get("f017n_yb003").asDouble());
            statement.setString(33, data.get("f018v_yb003").textValue());
            statement.setString(34, data.get("f019v_yb003").textValue());
            statement.setLong(35, data.get("seq").longValue());
            int i = statement.executeUpdate();
            LOG.info("update {} row", i);
        }finally {
            connection.commit();
            connection.close();
        }
    }
}
