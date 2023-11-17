//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import com.pav2py.excel.model.abstraction.AbstarctTestRow;
import com.pav2py.excel.utlilty.BasicUtilities;


/**
 * Model for Comment row from TestGateSheet
 * 
 * @author PJA7COB
 */
public class CommentRow extends AbstarctTestRow {

    private String comment;

    private ChapterRow group;
   
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ChapterRow getGroup() {
        return group;
    }

    public void setGroup(ChapterRow group) {
        this.group = group;
    }

    @Override
    public String toPy() {
        if (getComment() != null && !getComment().isEmpty()) {
            return "#COMMENT LINE : " + BasicUtilities.removeLineFeeds(getComment());
        }
        return "";
    }

}
