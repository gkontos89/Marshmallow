package Messaging;

import java.io.IOException;

import Messaging.Demo.HeartBeat;

public class HeartBeatWrappedMsg implements MarshmallowMessage {
	
	protected HeartBeat myMsg;
	
	public HeartBeatWrappedMsg(HeartBeat protoMsg)
	{
		myMsg = protoMsg;
	}
	
	@Override
	public Comparable<?> getMyIdData() {
		return myMsg.getId();
	}

	@Override
	public int getDataSize() {
		return myMsg.getSerializedSize();
	}

	@Override
	public Comparable<?> getMyIdDefaultValue() {
		return "HeartBeat";
	}

	@Override
	public byte[] getAsByteArray() throws IOException {
		return myMsg.toByteArray();
	}

	@Override
	public void fillFromByteArray(byte[] input) throws IOException {
		myMsg = HeartBeat.parseFrom(input);
	}

	@Override
	public boolean canParse(byte[] input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MarshmallowMessage getClone() {
		return new HeartBeatWrappedMsg(myMsg.toBuilder().build());
	}

}