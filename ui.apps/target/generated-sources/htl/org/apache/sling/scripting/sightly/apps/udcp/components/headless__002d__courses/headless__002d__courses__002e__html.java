/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.udcp.components.headless__002d__courses;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class headless__002d__courses__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_clientlib = null;
Object _dynamic_properties = bindings.get("properties");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
out.write("\n\n");
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "css");
    {
        String var_templateoptions1_field$_categories = "udcp.headless";
        {
            java.util.Map var_templateoptions1 = obj().with("categories", var_templateoptions1_field$_categories);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n");
{
    Object var_templatevar2 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "js");
    {
        String var_templateoptions3_field$_categories = "udcp.headless";
        {
            java.util.Map var_templateoptions3 = obj().with("categories", var_templateoptions3_field$_categories);
            callUnit(out, renderContext, var_templatevar2, var_templateoptions3);
        }
    }
}
out.write("\n\n<section class=\"cmp-headless-courses\" data-cmp-is=\"headless-courses\"");
{
    Object var_attrvalue8 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "endpoint"), "uri");
    {
        boolean var_shoulddisplayattr11 = ((!"".equals(var_attrvalue8)) && (!((Object)false).equals(var_attrvalue8)));
        if (var_shoulddisplayattr11) {
            out.write(" data-endpoint");
            {
                boolean var_istrueattr10 = (var_attrvalue8.equals(true));
                if (!var_istrueattr10) {
                    out.write("=\"");
                    out.write(renderContext.getObjectModel().toString(var_attrvalue8));
                    out.write("\"");
                }
            }
        }
    }
}
{
    Object var_attrvalue_id4 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "id");
    {
        Object var_attrvalueescaped_id5 = renderContext.call("xss", var_attrvalue_id4, "attribute", "id");
        {
            boolean var_shoulddisplayattr_id7 = (((null != var_attrvalueescaped_id5) && (!"".equals(var_attrvalueescaped_id5))) && ((!"".equals(var_attrvalue_id4)) && (!((Object)false).equals(var_attrvalue_id4))));
            if (var_shoulddisplayattr_id7) {
                out.write(" id");
                {
                    boolean var_istruevalue_id6 = (var_attrvalue_id4.equals(true));
                    if (!var_istruevalue_id6) {
                        out.write("=\"");
                        out.write(renderContext.getObjectModel().toString(var_attrvalueescaped_id5));
                        out.write("\"");
                    }
                }
            }
        }
    }
}
out.write(">\n\n    <div class=\"cmp-headless-courses__header\">\n        <h2 class=\"cmp-headless-courses__title\">\n            Explore Our Academic Programs\n        </h2>\n\n        <p class=\"cmp-headless-courses__intro\">\n            Discover the programs that prepare you for your future.\n        </p>\n    </div>\n\n    <p class=\"cmp-headless-courses__status\" data-headless-courses-status role=\"status\" aria-live=\"polite\">\n        Loading courses...\n    </p>\n\n    <div class=\"cmp-headless-courses__grid\" data-headless-courses-results>\n    </div>\n\n</section>");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

