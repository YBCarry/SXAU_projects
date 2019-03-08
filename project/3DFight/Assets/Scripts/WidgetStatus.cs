using UnityEngine;
using System.Collections;

//控制角色状态（生命值以及能量值）
public class WidgetStatus : MonoBehaviour {

    public float hpHealth = 10f;  //生命值
    public float maxHpHealth = 10f;  //最大生命值
    public float boost = 10f;  //能量值
    public float maxBoost = 10f;  //最大能量值

    public float widgetUseBoost = 5f;  //使用的能量值
    private Move move;

    public WidgetAnimation animationState;

    public CharacterController controller;

    public AudioClip getHitSound;  //受到攻击的音效
    public AudioClip deathSound;  //死亡的音效


    void Start()
    {
        move = GetComponent<Move>();
        animationState = GetComponent<WidgetAnimation>();
        controller = GetComponent<CharacterController>();
    }

    //增加生命值
    public void AddHealth(float health)
    {
        hpHealth += health;
        if (hpHealth >= maxHpHealth)
        {
            hpHealth = maxHpHealth;
        }
    }
    //减少生命值
    public void ApplyDamage(float damage)
    {
        //添加音效
        if (getHitSound)
        {
            AudioSource au = GetComponent<AudioSource>();
            au.clip = getHitSound;
            au.Play();
        }

        hpHealth -= damage;
        if (hpHealth <= 0)
        {
            hpHealth = 0;
            StartCoroutine(Die());
        }
    }

    //增加能量值
    public void AddEnergy(float energy)
    {
        boost += energy;
        if (boost >= maxBoost)
        {
            boost = maxBoost;
        }
    }

    //死亡
    IEnumerator Die()
    {
        Debug.Log("You character dies...");

        //添加音效
        if (deathSound)
        {
            AudioSource au = GetComponent<AudioSource>();
            au.clip = deathSound;
            au.Play();
        }

        //释放玩家的控制权
        move.isControllable = false;
        //播放死亡动画
        animationState.playDie();
        //等待时间
        yield return StartCoroutine(WaitForDie());
        //隐藏死亡角色
        HideCharacter();
        //等待几秒时间
        yield return StartCoroutine(WaitForThreeSeconds());
        //复活角色
        if (CheckPoint.isActivePT)
        {
            controller.transform.position = CheckPoint.isActivePT.transform.position;
            controller.transform.position = new Vector3(controller.transform.position.x, controller.transform.position.y + 0.5f, controller.transform.position.z);
        }
        ShowCharacter();
        animationState.ReBorn();
        //重置生命值
        hpHealth = maxHpHealth;
    }

    //等待时间
    IEnumerator WaitForDie()
    {
        Debug.Log(1);
        yield return new WaitForSeconds(3.5f);  //等待3.5s（播放动画）
    }
    IEnumerator WaitForThreeSeconds()
    {
        Debug.Log(2);
        yield return new WaitForSeconds(5f);  //等待5s（复活时间）
    }

    //隐藏角色
    void HideCharacter()
    {
        Debug.Log(3);
        GameObject.FindGameObjectWithTag("Body1").GetComponent<SkinnedMeshRenderer>().enabled = false;
        GameObject.FindGameObjectWithTag("Wheels1").GetComponent<SkinnedMeshRenderer>().enabled = false;
        move.isControllable = false;
    }

    //复活角色
    void ShowCharacter()
    {
        Debug.Log(4);
        GameObject.FindGameObjectWithTag("Body1").GetComponent<SkinnedMeshRenderer>().enabled = true;
        GameObject.FindGameObjectWithTag("Wheels1").GetComponent<SkinnedMeshRenderer>().enabled = true;
        move.isControllable = true;
    }


}
