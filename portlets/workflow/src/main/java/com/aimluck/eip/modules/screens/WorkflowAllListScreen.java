/*
 * Aipo is a groupware program developed by TOWN, Inc.
 * Copyright (C) 2004-2015 TOWN, Inc.
 * http://www.aipo.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aimluck.eip.modules.screens;

import org.apache.jetspeed.services.logging.JetspeedLogFactoryService;
import org.apache.jetspeed.services.logging.JetspeedLogger;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.aimluck.eip.util.ALEipUtils;
import com.aimluck.eip.workflow.WorkflowAllSelectData;

/**
 * ワークフローの管理者用の一覧を処理するクラスです。 <br />
 *
 */
public class WorkflowAllListScreen extends WorkflowAllScreen {

  /** logger */
  private static final JetspeedLogger logger = JetspeedLogFactoryService
    .getLogger(WorkflowAllListScreen.class.getName());

  /**
   *
   * @param rundata
   * @param context
   * @throws Exception
   */
  @Override
  protected void doOutput(RunData rundata, Context context) throws Exception {

    try {
      WorkflowAllSelectData listData = new WorkflowAllSelectData();
      listData.initField();
      listData.loadCategoryList(rundata, context);
      listData.loadRouteList(rundata, context);
      listData.setRowsNum(Integer.parseInt(ALEipUtils.getPortlet(
        rundata,
        context).getPortletConfig().getInitParameter("p1b-rows")));
      listData.doViewList(this, rundata, context);

      context.put("all_flg", true);

      String layout_template = "portlets/html/ajax-workflow-list-admin.vm";
      setTemplate(rundata, context, layout_template);

    } catch (Exception ex) {
      logger.error("[WorkflowAllListScreen] Exception.", ex);
      ALEipUtils.redirectDBError(rundata);
    }
  }
}
