package improbable.behaviours.player.controls

import com.typesafe.scalalogging.Logger
import improbable.papi.entity.EntityBehaviour
import improbable.physical.RaycastRequestWriter

class RaycastRequestorBehaviour(raycastRequestWriter: RaycastRequestWriter, logger : Logger) extends EntityBehaviour with RaycastRequestorInterface {
  def performRaycast(): Unit = {
    raycastRequestWriter.update.triggerRaycastRequested().finishAndSend()
  }
}