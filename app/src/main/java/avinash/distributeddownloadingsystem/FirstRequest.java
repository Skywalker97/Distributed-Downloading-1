package avinash.distributeddownloadingsystem;

import org.json.JSONObject;

/**
 * Created by DravitLochan on 13-03-2017.
 */

public class FirstRequest extends JSONObject {
    String url;
    int parts;

    public FirstRequest(String url, int parts)
    {
        this.url=url;
        this.parts=parts;
    }

    public String getUrl()
    {
        return url;
    }

    public int getParts()
    {
        return parts;
    }
}
