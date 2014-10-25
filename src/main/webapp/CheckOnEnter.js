/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function pwdCheck() 
{
    if (document.getElementById('password').value === document.getElementById('password2').value)
    {	
        document.getElementById('pwdMsg').style.color = "green";
        document.getElementById('pwdMsg').innerHTML = 'Passwords match!';
    }
    else
    {
        document.getElementById('pwdMsg').innerHTML = 'Passwords do not match. Please re-enter.';
        document.getElementById('pwdMsg').style.color = "red";
    }
}
    document.getElementById('password').onchange = pwdCheck;
    document.getElementById('password2').onchange = pwdCheck;

    
function emailCheck() 
{
    if (document.getElementById('email').value === document.getElementById('email2').value)
    {	
        document.getElementById('emailMsg').style.color = "green";
        document.getElementById('emailMsg').innerHTML = 'Email addresses match!';
     
    }
    else
    {
        document.getElementById('emailMsg').innerHTML = 'Email addresses do not match. Please re-enter.';
        document.getElementById('emailMsg').style.color = "red";
    }
}
    document.getElementById('email').onchange = emailCheck;
    document.getElementById('email2').onchange = emailCheck;