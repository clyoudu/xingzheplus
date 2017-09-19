!function () {
    function e() {
        r = $("#voteCaptchaInput").val(), setTimeout(function () {
            1 != $("#imgCaptcha").children("ul").length && $("#getCodeBtn").attr("disabled", !1)
        }, 500)
    }

    var t = window.location.search;
    if (t.indexOf("?account") && $("#setPswAccounts").val(t.slice(9)), -1 !== document.cookie.indexOf("dandan")) {
        !function (e) {
            for (var t, o = document.cookie, n = o.split("; "), s = n.length, a = 0; a < s; a++) {
                var r = n[a].split("=");
                if (e === r[0]) {
                    t = r[1];
                    break
                }
            }
            var i = t.length;
            if (i % 4 != 0)for (var c = 0; c < 4 - i % 4; c++)t += "=";
            t = base64decode(t), t = t.split(":"), "1" === t[1] ? $("#goUserpageLink").hide() : $("#goUserpageLink").show().attr("href", "/portal/#!/"), t[0]
        }("dandan")
    }
    var o = function (e) {
        for (var t = document.cookie, o = t.split("; "), n = 0; n < o.length; n++) {
            var s = o[n].split("=");
            if (e === s[0])return s[1]
        }
        return ""
    }("rd"), n = $("#pubkey").val(), s = $("#voteCaptchaImg"), a = function () {
        $.get("/api/v4/captcha_reg", function () {
            s.attr("src", "/api/v4/captcha_reg")
        })
    };
    a(), s.on("click", function () {
        a()
    });
    var r = "";
    $("#getCodeBtn").attr("disabled", !0), $("#voteCaptchaInput").keyup(function () {
        4 == $("#voteCaptchaInput").val().length ? e() : $("#getCodeBtn").attr("disabled", !0)
    }), $("#getCodeBtn").on("click", function (e) {
        if ($(this).attr("disabled")) e.preventDefault(); else {
            var t = $("#signupAccountPhone").val(), o = $("#getCodeInput"), n = $("#getCodeBtn"), s = n.data("type"),
                a = {account: t, type: s, isvoice: 0, code: r}, i = {account: t, type: s, isvoice: 1, code: r};
            t.length && ($(this).attr("disabled", "disabled"), $.ajax({
                url: "/api/v4/send_msg",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(a)
            }).done(function () {
                o.focus(), n.text("60秒");
                var e = (new Date).getTime(), t = setInterval(function () {
                    var o = (new Date).getTime(), s = Math.ceil(60 - (o - e) / 1e3);
                    s > 0 ? n.text(s + "秒") : (clearInterval(t), n.removeAttr("disabled").text("短信获取"), $(".form-group-voice-getcode").show(), $("#signupVoiceGetCodeBtn").one("click", function (e) {
                        e.preventDefault(), $.ajax({
                            url: "/api/v4/send_msg",
                            type: "POST",
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(i)
                        }).done(function () {
                            showServerMsg("获取成功，请注意接听电话语音。", "serve-msg-success"), $(".form-group-voice-getcode").hide(), console.log("success")
                        }).fail(function (e) {
                            showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error"), n.removeAttr("disabled").text("短信获取"), alert("1")
                        })
                    }))
                }, 1e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error"), n.removeAttr("disabled").text("短信获取")
            }))
        }
    }), $("#loginForm").validator().on("submit", function (e) {
        if (e.isDefaultPrevented()) console.log("没通过验证"); else {
            e.preventDefault();
            var s = $("#loginAccount").val(), a = $("#loginPassword").val() + ";" + o, r = new JSEncrypt;
            r.setPublicKey(n), a = r.encrypt(a);
            var i = {account: s, password: a, source: "web"};
            $.ajax({
                url: "/api/v4/account/login",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(i)
            }).done(function (e) {
                showServerMsg("登录成功！即将跳转…", "serve-msg-success");
                var o;
                o = t.length > 0 && 0 === t.indexOf("?next") ? location.search.substr(6) : "/portal/#!/", setTimeout(function () {
                    window.location.assign(o)
                }, 2e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error")
            })
        }
    }), $("#signupForm").validator().on("submit", function (e) {
        if (e.isDefaultPrevented()); else {
            event.preventDefault();
            var t = $("#signupPassword").val() + ";" + o, s = new JSEncrypt;
            s.setPublicKey(n), t = s.encrypt(t);
            var a = $("#signupAccountPhone").val(), r = $("#getCodeInput").val(),
                i = {ltype: 0, phone: a, password: t, keycode: parseInt(r), source: "web"};
            $.ajax({
                url: "/api/v4/account/register",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(i)
            }).done(function (e) {
                showServerMsg("注册成功！", "serve-msg-success"), setTimeout(function () {
                    window.location.assign("/")
                }, 2e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error")
            })
        }
    }), $("#signupEmailForm").validator().on("submit", function (e) {
        if (e.isDefaultPrevented()); else {
            event.preventDefault();
            var t = $("#signupEmailPassword").val() + ";" + o, s = new JSEncrypt;
            s.setPublicKey(n), t = s.encrypt(t);
            var a = $("#signupEmailAccount").val(), r = {ltype: 1, email: a, password: t, source: "web"};
            $.ajax({
                url: "/api/v4/account/register",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(r)
            }).done(function (e) {
                showServerMsg("注册成功！", "serve-msg-success"), setTimeout(function () {
                    window.location.assign("/")
                }, 2e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error")
            })
        }
    }), $("#setPasswordForm").validator().on("submit", function (e) {
        var t = $("#setPswPasswordInput");
        if (e.isDefaultPrevented()); else {
            e.preventDefault();
            var s = t.val() + ";" + o, a = new JSEncrypt;
            a.setPublicKey(n), s = a.encrypt(s), data = {password: s}, $.ajax({
                url: "/api/v4/account/set_password",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(data)
            }).done(function () {
                showServerMsg("密码设置成功！", "serve-msg-success"), setTimeout(function () {
                    window.location.assign("/")
                }, 2e3)
            }).fail(function () {
                console.log("error")
            })
        }
    }), $("#bindAccountForm").on("click", "#toggleAccountTypeBtn", function (e) {
        $(this).hasClass("bind-phone") ? ($(".form-group-getcode").hide(), $(".form-group-phone").hide(), $(".form-group-email").show(), $(this).text("使用手机号绑定").removeClass("bind-phone")) : ($(".form-group-getcode").show(), $(".form-group-phone").show(), $(".form-group-email").hide(), $(this).text("使用邮箱绑定").addClass("bind-phone"))
    }), $("#bindAccountForm").validator().on("submit", function (e) {
        if (e.isDefaultPrevented()); else {
            event.preventDefault();
            var s = $("#signupPassword").val() + ";" + o, a = new JSEncrypt;
            a.setPublicKey(n), s = a.encrypt(s);
            var r, i = $("#signupAccountPhone").val(), c = $("#signupAccountEmail").val(), p = $("#getCodeInput").val();
            r = $("#toggleAccountTypeBtn").hasClass("bind-phone") ? {
                account: i,
                keycode: parseInt(p),
                password: s,
                source: "web"
            } : {account: c, password: s, source: "web"}, $.ajax({
                url: "/api/v4/account/bind_account",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(r)
            }).done(function (e) {
                showServerMsg("绑定成功！跳转中...", "serve-msg-success");
                var o;
                o = t.length > 0 && 0 === t.indexOf("?next") ? location.search.substr(6) : "/portal/#!/", setTimeout(function () {
                    window.location.assign(o)
                }, 2e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error"), $("#getCodeBtn").removeAttr("disabled").text("短信获取")
            })
        }
    }), $("#bindRegisteredAccountForm").validator().on("submit", function (e) {
        if (e.isDefaultPrevented()) console.log("没通过验证"); else {
            e.preventDefault();
            var s = $("#loginAccount").val(), a = $("#loginPassword").val() + ";" + o, r = new JSEncrypt;
            r.setPublicKey(n), a = r.encrypt(a);
            var i = {account: s, password: a, source: "web"};
            $.ajax({
                url: "/api/v4/account/bind_registered_account",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(i)
            }).done(function (e) {
                showServerMsg("登录成功！跳转中…", "serve-msg-success");
                var o;
                o = t.length > 0 && 0 === t.indexOf("?next") ? location.search.substr(6) : "/portal/#!/", setTimeout(function () {
                    window.location.assign(o)
                }, 2e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error")
            })
        }
    });
    var i = $("#FindPasswordForm");
    i.on("click", "#fpEmailToggle", function () {
        "phone" === i.attr("data-ftype") ? (i.attr("data-ftype", "email"), $(".form-show-phone").hide(), $(".form-show-email").show(), $(this).removeClass("fpemail-show").text("手机找回"), $("button[type=submit]").text("发送邮件")) : (i.attr("data-ftype", "phone"), $(".form-show-phone").show(), $(".form-show-email").hide(), $(this).addClass("fpemail-show").text("邮箱找回"), $("button[type=submit]").text("设置新密码"))
    }), i.validator().on("submit", function (e) {
        if (e.isDefaultPrevented()); else {
            e.preventDefault();
            var t = $("#fpPassword").val() + ";" + o, s = new JSEncrypt;
            s.setPublicKey(n), t = s.encrypt(t);
            var a, r = $("#signupAccountPhone").val(), c = $("#getCodeInput").val(), p = $("#fpInputEmail").val(),
                u = "";
            "phone" === i.attr("data-ftype") ? (a = {
                account: r,
                password: t,
                keycode: parseInt(c)
            }, u = "设置成功！请使用新密码登录") : (a = {account: p}, u = "邮件发送成功！"), $.ajax({
                url: "/user/find_password",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(a)
            }).done(function (e) {
                showServerMsg(u, "serve-msg-success"), setTimeout(function () {
                    window.location.assign("/user/login")
                }, 2e3)
            }).fail(function (e) {
                showServerMsg(JSON.parse(e.responseText).error_message, "serve-msg-error"), $("#getCodeBtn").removeAttr("disabled").text("短信获取")
            })
        }
    })
}();