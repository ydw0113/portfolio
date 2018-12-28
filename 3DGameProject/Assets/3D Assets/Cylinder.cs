using UnityEngine;
using System.Collections;

public class Cylinder : MonoBehaviour {
	GameObject Warg;
	GameObject Skel;
	GameObject Dragon;
	GameObject director2;
	// Use this for initialization
	void Start () {
		this.director2 =GameObject.FindWithTag("Player");
		this.Warg = GameObject.FindWithTag ("Warg");
		this.Skel = GameObject.FindWithTag ("Skel");
		this.Dragon = GameObject.FindWithTag ("Dragon");


	}

	// Update is called once per frame
	void Update () {

	}
	void OnTriggerEnter(Collider other){

		if(other.gameObject.tag=="Warg"){
			//GameObject.FindWithTag("Warg").GetComponent<CharacterStatus> ().HP = -30;
			//GameObject director2 = GameObject.FindWithTag("Player");
			director2.GetComponent<CharacterStatus> ().levelup (50);
			Destroy(Warg);
		}
		if(other.gameObject.tag=="Skel"){
			//GameObject.FindWithTag("Warg").GetComponent<CharacterStatus> ().HP = -30;
			//GameObject director2 = GameObject.FindWithTag("Player");
			director2.GetComponent<CharacterStatus> ().levelup (100);
			Destroy(Skel);
		}
		if(other.gameObject.tag=="Dragon"){
			//GameObject.FindWithTag("Warg").GetComponent<CharacterStatus> ().HP = -30;

			director2.GetComponent<CharacterStatus> ().levelup (200);
			Destroy(Dragon);
		}

	}
}
