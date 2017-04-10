package avinash.distributeddownloadingsystem;

import org.json.JSONObject;

/**
 * Created by Avinash Sharma on 10-Apr-17.
 */

public class DownloadPartition_Response extends JSONObject {

    private final String _ID = "_id";//Unique Id
    private final String Name = "name";
    private final String EXT = "ext";//File Extension
    private final String PART_COUNT = "partCount";//Number of partitions
    private final String _V = "_v";
    private final String REASON = "reason";//???
    private final String CREATED_ON = "createdOn";//Date
    private final String STATUS = "status";// Downloading/Done/failed

}
