import { NativeModules, Platform } from 'react-native'

const OS = Platform.OS

const Umeng = NativeModules.UmengModule

export function isWXAppInstalled() {
	return new Promise((res) => {
		Umeng.isWXAppInstalled().then((data) => {
			res(Number(data))
		}).catch((err) => {
			res({ code: 400, msg: err.toString() })
		})
	})
}

export function isQQAppInstalled() {
	return new Promise((res) => {
		Umeng.isQQAppInstalled().then((data) => {
			res(Number(data))
		}).catch((err) => {
			res({ code: 400, msg: err.toString() })
		})
	})
}

export function isWBAppInstalled() {
	return new Promise((res) => {
		Umeng.isWBAppInstalled().then((data) => {
			res(Number(data))
		}).catch((err) => {
			res({ code: 400, msg: err.toString() })
		})
	})
}

export function WXLogin() {
	return new Promise((res) => {
		Umeng.WXLogin().then((r) => {
			const data = JSON.parse(r)
			if (Number(data.isLogin)) res({ code: 200, data })
			if (!Number(data.isLogin)) res({ code: 400, msg: data.errmsg })
		}).catch((err) => {
			res({ code: 400, msg: err.errmsg })
		})
	})
}

export function QQLogin() {
	return new Promise((res) => {
		Umeng.QQLogin().then((r) => {
			const data = JSON.parse(r)
			if (Number(data.isLogin)) res({ code: 200, data })
			if (!Number(data.isLogin)) res({ code: 400, msg: data.errmsg })
		}).catch((err) => {
			res({ code: 400, msg: err.errmsg })
		})
	})
}

export function WBLogin() {
	return new Promise((res) => {
		Umeng.WBLogin().then((r) => {
			const data = JSON.parse(r)
			if (Number(data.isLogin)) res({ code: 200, data })
			if (!Number(data.isLogin)) res({ code: 400, msg: data.errmsg })
		}).catch((err) => {
			res({ code: 400, msg: err.errmsg })
		})
	})
}

export function shareWxTimeline(obj) {
	return Umeng.shareWxTimeline(OS === 'ios' ? obj : JSON.stringify(obj))
}

export function shareWxSession(obj) {
	return Umeng.shareWxSession(OS === 'ios' ? obj : JSON.stringify(obj))
}

export function shareQzone(obj) {
	return Umeng.shareQzone(OS === 'ios' ? obj : JSON.stringify(obj))
}

export function shareQQ(obj) {
	return Umeng.shareQQ(OS === 'ios' ? obj : JSON.stringify(obj))
}

export function shareWB(obj) {
	return Umeng.shareWB(OS === 'ios' ? obj : JSON.stringify(obj))
}
