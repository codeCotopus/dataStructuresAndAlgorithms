package adventofcode.aoc2022.day13;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class PacketPair {
    final Packet firstPacket;
    final Packet secondPacket;

    public static PacketPair of(final String nonSeparatedPackets) {
        String[] packets = nonSeparatedPackets.lines().toArray(String[]::new);
        return new PacketPair(Packet.of(packets[0]), Packet.of(packets[1]));
    }


    public boolean isOrdered() {
        if (secondPacket.getData().length < firstPacket.getData().length) {
            return false;
        }
        for (int i = 0; i < firstPacket.getData().length; i++) {
            if (firstPacket.getData()[i] > secondPacket.getData()[i])
                return false;
        }
        return true;

    }
}

