package improbable.behaviours.player.controls.physical

import improbable.behaviours.player.controls.RaycastRequestorInterface
import improbable.entity.physical.RigidbodyInterface
import improbable.papi.entity.{Entity, EntityBehaviour}
import improbable.player.controls.PlayerControlsState
import improbable.player.physical.PlayerStateWriter

class PlayerBehaviour(entity: Entity,
                      playerState: PlayerStateWriter,
                      rigidbodyInterface: RigidbodyInterface,
                      raycastInterface: RaycastRequestorInterface) extends EntityBehaviour {

  override def onReady(): Unit = {
    entity.watch[PlayerControlsState].bind.movementDirection {
      movementDirection =>
        rigidbodyInterface.setForce(movementDirection * playerState.forceMagnitude)
    }

    entity.watch[PlayerControlsState].onExtinguishRequested {
      _ => {
        raycastInterface.performRaycast()
      }
    }
  }

}
