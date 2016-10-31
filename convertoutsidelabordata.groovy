import java.nio.file.Paths

@Grab('org.apache.commons:commons-csv:1.2')
import org.apache.commons.csv.CSVParser
import static org.apache.commons.csv.CSVFormat.*

import groovy.json.JsonOutput

def listing = []

Paths.get('file/bptms-Outside_labour_force_by_state.csv').withReader { reader ->
    CSVParser csv = new CSVParser(reader, DEFAULT.withHeader())
    for (record in csv.iterator()) {
        listing << record.toMap()
    }
}

println listing

Paths.get('convertedoutsidelabourdata.json').withWriter { jsonWriter ->
    jsonWriter.write JsonOutput.prettyPrint(JsonOutput.toJson(listing))
}
