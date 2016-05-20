// Generated by ProtocGenFabric. DO NOT EDIT!
// source: schema.improbable.corelib.entity.nature.NatureDataData in improbable/corelib/entity/nature/nature_data.proto.

namespace Improbable.Corelib.Entity.Nature
{
public struct NatureDataData : global::System.IEquatable<NatureDataData>
{
    public readonly global::Improbable.Util.Collections.IReadOnlyList<string> Natures;

    public NatureDataData (global::Improbable.Util.Collections.IReadOnlyList<string> natures)
    {
        Natures = natures;
    }

    public override bool Equals(object obj)
    {
        if (!(obj is NatureDataData))
            return false;
        return Equals((NatureDataData) obj);
    }

    public static bool operator ==(NatureDataData obj1, NatureDataData obj2)
    {
        return obj1.Equals(obj2);
    }

    public static bool operator !=(NatureDataData obj1, NatureDataData obj2)
    {
        return !obj1.Equals(obj2);
    }

    public bool Equals(NatureDataData obj)
    {
        return true
            && global::Improbable.Util.Collections.CollectionUtil.ListsEqual(Natures, obj.Natures);
    }

    public override int GetHashCode()
    {
        int res = 1327;
        res = (res * 977) + (Natures != null ? Natures.GetHashCode() : 0);
        return res;
    }
}

//For internal use only, not to be used in user code.
public sealed class NatureDataDataHelper : global::Improbable.Tools.IProtoConverter<Improbable.Corelib.Entity.Nature.NatureDataData, Schema.Improbable.Corelib.Entity.Nature.NatureDataData>
{
    static readonly NatureDataDataHelper _instance = new NatureDataDataHelper();
    public static NatureDataDataHelper Instance { get { return _instance; } }
    private NatureDataDataHelper() {}

    public Schema.Improbable.Corelib.Entity.Nature.NatureDataData ToProto(Improbable.Corelib.Entity.Nature.NatureDataData data)
    {
        var proto = new Schema.Improbable.Corelib.Entity.Nature.NatureDataData();
        global::Improbable.Tools.ToProto<string, string>(data.Natures, proto.Natures);
        return proto;
    }

    //Shallow merge method
    public Improbable.Corelib.Entity.Nature.NatureDataData MergeFromProto(Schema.Improbable.Corelib.Entity.Nature.NatureDataData proto, bool[] statesToClear, Improbable.Corelib.Entity.Nature.NatureDataData data)
    {
        return new Improbable.Corelib.Entity.Nature.NatureDataData(
            (proto.Natures.Count > 0 || statesToClear != null && statesToClear[0]) ? global::Improbable.Tools.FromProto<string, string>(proto.Natures) : data.Natures
        );
    }

    public Improbable.Corelib.Entity.Nature.NatureDataData FromProto(Schema.Improbable.Corelib.Entity.Nature.NatureDataData proto)
    {
        return new Improbable.Corelib.Entity.Nature.NatureDataData(
            global::Improbable.Tools.FromProto<string, string>(proto.Natures)
        );
    }

    //Shallow merge method
    public void MergeProto(Schema.Improbable.Corelib.Entity.Nature.NatureDataData protoFrom, bool[] statesToClearFrom, Schema.Improbable.Corelib.Entity.Nature.NatureDataData protoTo, bool[] statesToClearTo)
    {
        if ((protoFrom.Natures.Count > 0 || statesToClearFrom != null && statesToClearFrom[0]))
        {
            statesToClearTo[0] = statesToClearFrom[0];
            protoTo.Natures.Clear();
            protoTo.Natures.AddRange(protoFrom.Natures);
        }
    }
}
}
