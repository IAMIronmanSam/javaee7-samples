/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.sample.serversentevent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ChunkedOutput;

/**
 * @author Arun Gupta
 */
@Path("fruits")
public class MyResource {

    private final SseBroadcaster broadcaster = new SseBroadcaster() {
            @Override
            public void onException(ChunkedOutput<OutboundEvent> outboundEventChunkedOutput, Exception exception) {
                exception.printStackTrace();
            }
        };
    
    static EventOutput output = new EventOutput();
    
    @GET
    @Path("list")
    @Produces(MediaType.TEXT_PLAIN)
    public String fruits() {
        return FruitDatabase.list();
    }
    
    @GET
    @Path("sse")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput sse() {
        System.out.println("Registered");
//        EventOutput channel = new EventOutput();
//        broadcaster.add(channel);
//        return channel;
        return output;
    }

    @POST
    @Path("{name}")
    public void add(@PathParam("name")String name) {
        System.out.println("Adding " + name);
        FruitDatabase.add(name);
        try {
            output.write(new OutboundEvent.Builder()
                    .name("add")
                    .data(String.class, name)
                    .build());
        } catch (IOException ex) {
            Logger.getLogger(MyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
//        broadcaster.broadcast(new OutboundEvent.Builder()
//                .name("add")
//                .build());
//                .build());
    }

    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name")String name) {
        System.out.println("Removing " + name);
        String op = FruitDatabase.remove(name) ? "delete": "noop";
        try {
            output.write(new OutboundEvent.Builder()
                    .name(op)
                    .data(String.class, name)
                    .build());
        } catch (IOException ex) {
            Logger.getLogger(MyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
//        broadcaster.broadcast(new OutboundEvent.Builder()
//                .name(op)
//                .data(String.class, name)
//                .build());
    }
}
