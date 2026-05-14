package benchmark;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PerformanceEvaluator {

    public void benchmarkBatchInsert(int recordCount) {

        String query =
                "INSERT INTO Members VALUES (?, ?, 0)";

        try (
                Connection con =
                        ConnectionManager.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            long start = System.nanoTime();

            for (int i = 1000; i < 1000 + recordCount; i++) {

                ps.setInt(1, i);
                ps.setString(2, "Member_" + i);

                ps.addBatch();
            }

            ps.executeBatch();

            long end = System.nanoTime();

            double ms =
                    (end - start) / 1_000_000.0;

            System.out.println(
                    "\n===== BATCH INSERT PERFORMANCE ====="
            );

            System.out.println(
                    "Inserted Records: " + recordCount
            );

            System.out.println(
                    "Execution Time: " + ms + " ms"
            );

            System.out.println(
                    "Throughput: " +
                            (recordCount / (ms / 1000)) +
                            " ops/sec"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
