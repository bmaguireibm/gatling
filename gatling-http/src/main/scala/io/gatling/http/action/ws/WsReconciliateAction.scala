/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.http.action.ws

import akka.actor.ActorRef
import io.gatling.core.session._
import io.gatling.http.action.RequestAction

class WsReconciliateAction(val requestName: Expression[String], wsName: String, val next: ActorRef) extends RequestAction {

  def sendRequest(requestName: String, session: Session) = {
    for {
      wsActor <- session(wsName).validate[ActorRef]
    } yield wsActor ! Reconciliate(requestName, next, session)
  }
}
