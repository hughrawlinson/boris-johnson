// Generated by ProtocGenFabric. DO NOT EDIT!
// source: schema.improbable.player.LocalPlayerCheckStateData in improbable/player/local_player_check_state.proto.

namespace Improbable.Player
{
public struct LocalPlayerCheckStateData : global::System.IEquatable<LocalPlayerCheckStateData>
{


    public override bool Equals(object obj)
    {
        if (!(obj is LocalPlayerCheckStateData))
            return false;
        return Equals((LocalPlayerCheckStateData) obj);
    }

    public static bool operator ==(LocalPlayerCheckStateData obj1, LocalPlayerCheckStateData obj2)
    {
        return obj1.Equals(obj2);
    }

    public static bool operator !=(LocalPlayerCheckStateData obj1, LocalPlayerCheckStateData obj2)
    {
        return !obj1.Equals(obj2);
    }

    public bool Equals(LocalPlayerCheckStateData obj)
    {
        return true;
    }

    public override int GetHashCode()
    {
        int res = 1327;
        return res;
    }
}

//For internal use only, not to be used in user code.
public sealed class LocalPlayerCheckStateDataHelper : global::Improbable.Tools.IProtoConverter<Improbable.Player.LocalPlayerCheckStateData, Schema.Improbable.Player.LocalPlayerCheckStateData>
{
    static readonly LocalPlayerCheckStateDataHelper _instance = new LocalPlayerCheckStateDataHelper();
    public static LocalPlayerCheckStateDataHelper Instance { get { return _instance; } }
    private LocalPlayerCheckStateDataHelper() {}

    public Schema.Improbable.Player.LocalPlayerCheckStateData ToProto(Improbable.Player.LocalPlayerCheckStateData data)
    {
        var proto = new Schema.Improbable.Player.LocalPlayerCheckStateData();
        return proto;
    }

    //Shallow merge method
    public Improbable.Player.LocalPlayerCheckStateData MergeFromProto(Schema.Improbable.Player.LocalPlayerCheckStateData proto, bool[] statesToClear, Improbable.Player.LocalPlayerCheckStateData data)
    {
        return new Improbable.Player.LocalPlayerCheckStateData(

        );
    }

    public Improbable.Player.LocalPlayerCheckStateData FromProto(Schema.Improbable.Player.LocalPlayerCheckStateData proto)
    {
        return new Improbable.Player.LocalPlayerCheckStateData(

        );
    }

    //Shallow merge method
    public void MergeProto(Schema.Improbable.Player.LocalPlayerCheckStateData protoFrom, bool[] statesToClearFrom, Schema.Improbable.Player.LocalPlayerCheckStateData protoTo, bool[] statesToClearTo)
    {
    }
}
}
