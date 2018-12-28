using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class CharacterStatus : MonoBehaviour {
	
	//---------- 공격 장에서 사용한다. ----------
	// 체력.
	public int HP = 100;
	public int MaxHP = 100;
	public int exp = 0;
	public int Maxexp = 100;
	// 공격력.
	public int Power = 10;
	public int level = 1;
	// 마지막에 공격한 대상.
	public GameObject lastAttackTarget = null;
	GameObject text;
	//---------- GUI 및 네트워크 장에서 사용한다. ----------
	// 플레이어 이름.
	public string characterName = "Player";
	
	//--------- 애니메이션 장에서 사용한다. -----------
	// 상태.

	public bool attacking = false;
	public bool died = false;
	void Start () {
		this.text = GameObject.Find ("Text"); 
	
	}
	public void getItem(){
		HP += 10;
	}

	public void levelup(int a){
		exp += a;
		if (exp >= Maxexp) {
			GameObject director = GameObject.FindWithTag ("Player");
			director.GetComponent<CharacterStatus> ().level += 1;
			this.text.GetComponent<Text> ().text = "Level : "+director.GetComponent<CharacterStatus> ().level;
		}
	}

	void Update () {
		if (level == 2) {
			Maxexp = 200;
		} else if (level == 3) {
			Maxexp = 400;
		} else if (level == 4) {
			Maxexp = 600;
		} else if (level == 5) {
			Maxexp = 1000;
		} else if (level == 6) {
			Maxexp = 2000;
		} else if (level == 7) {
			Maxexp = 4000;
		} else if (level == 8) {
			Maxexp = 10000;
		} else if (level == 9) {
			Maxexp = 10000000;
		}


	}
}
