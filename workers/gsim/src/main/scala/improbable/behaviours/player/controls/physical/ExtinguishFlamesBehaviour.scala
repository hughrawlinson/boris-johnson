package improbable.behaviours.physical

import com.typesafe.scalalogging.Logger
import improbable.papi.entity.{Entity, EntityBehaviour}
import improbable.papi.world.World
import improbable.physical.RaycastResponse

class ExtinguishFlamesBehaviour(entity : Entity, world: World, logger : Logger) extends EntityBehaviour {
  entity.watch[RaycastResponse].onRaycastResponded {
    payload => {
      world.messaging.sendToEntity(payload.entityId, Extinguish)
    }
  }
}