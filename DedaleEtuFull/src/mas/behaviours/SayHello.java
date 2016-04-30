package mas.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SayHello extends TickerBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2058134622078521998L;

	/**
	 * An agent tries to contact its friend and to give him its current position
	 * @param myagent the agent who posses the behaviour
	 *  
	 */
	public SayHello (final Agent myagent) {
		super(myagent, 3000);
		//super(myagent);
	}

	@Override
	public void onTick() {
		System.out.println("Je tick sayHello");
		String myPosition=((mas.abstractAgent)this.myAgent).getCurrentPosition();

		ACLMessage msg=new ACLMessage(7);
		msg.setSender(this.myAgent.getAID());

		if (myPosition!=""){
			System.out.println("Agent "+this.myAgent.getLocalName()+ " is trying to reach its friends");
			msg.setContent("Hello World, I'm at "+myPosition);
			if (!myAgent.getLocalName().equals("Explo1")){
				msg.addReceiver(new AID("Explo1",AID.ISLOCALNAME));
			}else{
				msg.addReceiver(new AID("Explo2",AID.ISLOCALNAME));
			}

			((mas.abstractAgent)this.myAgent).sendMessage(msg);

		}
		
		final MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		final ACLMessage msg1 = this.myAgent.receive(msgTemplate);
		if (msg1 != null) {
			String cnt = msg1.getContent();
			System.out.println("<----Recu de: "+msg1.getSender()+" ,content= "+cnt); 
		}
	}

}