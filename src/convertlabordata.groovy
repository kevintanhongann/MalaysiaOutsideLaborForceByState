import java.nio.file.Paths

@Grab('org.apache.commons:commons-csv:1.2')
import org.apache.commons.csv.CSVParser
import static org.apache.commons.csv.CSVFormat.*

import groovy.json.JsonOutput

/**
 * Created by kevintan on 31/10/2016.
 */
class convertlabordata {

    public static void main(String[] args) {

        def listing = []

        Paths.get('file/bptms-Outside_labour_force_by_state.csv').withReader { reader ->
            CSVParser csv = new CSVParser(reader, DEFAULT.withHeader())
            for (record in csv.iterator()) {
                listing << record.toMap()
            }
        }

        println listing

        Paths.get('convertedlabourdata.json').withWriter { jsonWriter ->
            jsonWriter.write JsonOutput.prettyPrint(JsonOutput.toJson(listing))
        }
    }
}
