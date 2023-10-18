import UserService from "../service/UserService.js";

class UserController {

  async save(req, res) {
    const data = await UserService.save(req);
    return res.status(data.status).json(data);
  }

  async getAccessToken(req, res) {
    let accessToken = await UserService.getAccessToken(req);
    return res.status(accessToken.status).json(accessToken);
  }

  async findByEmail(req, res) {
    let user = await UserService.findByEmail(req);
    return res.status(user.status).json(user);
  }
}

export default new UserController();
