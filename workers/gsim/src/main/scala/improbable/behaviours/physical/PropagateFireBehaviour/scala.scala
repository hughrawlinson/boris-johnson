package improbable.behaviours.physical

import com.typesafe.scalalogging.Logger
import improbable.papi.entity.{Entity, EntityBehaviour}
import improbable.papi.world.World
import improbable.papi.world.messaging.CustomMsg
import improbable.physical.FireWriter

import scala.concurrent.duration._

case object Ignite extends CustomMsg
case object Extinguish extends CustomMsg

class PropagateFireBehaviour(fire: FireWriter, world: World, entity: Entity, logger: Logger) extends EntityBehaviour {

  if (fire.onFire) {
    ignite()
  }

  world.messaging.onReceive {
    case Ignite =>
      if (!fire.onFire) {
        ignite()
      }
    case Extinguish =>
      if (fire.onFire) {
        fire.update.onFire(false).temperature(32).finishAndSend()
      }
  }

  def ignite(): Unit = {
    fire.update.onFire(true).temperature(34).finishAndSend()
    spreadFire
  }

  def spreadFire(): Unit = {
    if (fire.onFire) {
      world.entities.find(entity.position, 5.0f).foreach {
        otherEntity =>
          world.messaging.sendToEntity(otherEntity.entityId, Ignite)
      }
      world.timing.after(500 millis) {
        spreadFire
      }
    }
  }
}