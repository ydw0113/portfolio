using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
public class HP : MonoBehaviour {
	GameObject Hp;

	// Use this for initialization
	void Start () {
		this.Hp = GameObject.Find ("Hp");
	}

	// Update is called once per frame
	void Update () {

	}
	public void DecreaseHp(){
		this.Hp.GetComponent<Image> ().fillAmount -= 0.1f;

	}
	public void DecreaseHp2(){
		this.Hp.GetComponent<Image> ().fillAmount -= 0.2f;

	}
	public void DecreaseHp3(){
		this.Hp.GetComponent<Image> ().fillAmount -= 0.3f;

	}
	public void creaseHp(){
		this.Hp.GetComponent<Image> ().fillAmount += 0.3f;

	}
}
