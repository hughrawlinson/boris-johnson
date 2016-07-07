package improbable.behaviours.player.controls

import improbable.papi.entity.EntityBehaviour
import improbable.papi.entity.Entity
import improbable.physical.RaycastResponse
import improbable.unity.fabric.PhysicsEngineConstraint

class RaycastResponderBehaviour(entity: Entity) extends EntityBehaviour {
  override def onReady(): Unit = {
    entity.delegateState[RaycastResponse](PhysicsEngineConstraint)
  }
}