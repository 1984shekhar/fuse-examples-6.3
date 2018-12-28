package org.jboss.fuse.samples.beans;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBean  {
    private static final Logger LOG = LoggerFactory.getLogger(TestBean.class);
    private String fileLoc;
    
    public void setFileLoc(String fileLocation) {
        this.fileLoc = fileLocation;
    }

    public void init() throws Exception {
    	
    	URL url = new URL(fileLoc);
    	InputStream ins = url.openStream();
    	final int bufferSize = 1024;
    	final char[] buffer = new char[bufferSize];
    	Reader in = new InputStreamReader(ins, "UTF-8");
    	final StringBuilder out = new StringBuilder();
    	for (; ; ) {
    	    int rsz = in.read(buffer, 0, buffer.length);
    	    if (rsz < 0)
    	        break;
    	    out.append(buffer, 0, rsz);
    	}
    	LOG.info(out.toString());;
    }
}
