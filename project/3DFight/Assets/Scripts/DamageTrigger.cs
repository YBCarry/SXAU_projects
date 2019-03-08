using UnityEngine;
using System.Collections;

public class DamageTrigger : MonoBehaviour {

    public float damage = 20f;
    public WidgetStatus playerStatus;

    void OnTriggerEnter(Collider coll)
    {
        if (coll.tag == "Enemy")
        {
            return;
        }
        playerStatus = GameObject.FindWithTag("Player").GetComponent<WidgetStatus>();
        playerStatus.ApplyDamage(damage);
    }
}
