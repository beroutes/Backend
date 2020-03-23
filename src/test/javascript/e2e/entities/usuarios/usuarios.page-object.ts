import { element, by, ElementFinder } from 'protractor';

export class UsuariosComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-usuarios div table .btn-danger'));
  title = element.all(by.css('jhi-usuarios div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getText();
  }
}

export class UsuariosUpdatePage {
  pageTitle = element(by.id('jhi-usuarios-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nombreInput = element(by.id('field_nombre'));
  apellidosInput = element(by.id('field_apellidos'));
  emailInput = element(by.id('field_email'));
  usuarioInput = element(by.id('field_usuario'));
  passwordInput = element(by.id('field_password'));
  fechaRegistroInput = element(by.id('field_fechaRegistro'));
  ciudadInput = element(by.id('field_ciudad'));
  urlFotoPerfilInput = element(by.id('field_urlFotoPerfil'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setNombreInput(nombre: string): Promise<void> {
    await this.nombreInput.sendKeys(nombre);
  }

  async getNombreInput(): Promise<string> {
    return await this.nombreInput.getAttribute('value');
  }

  async setApellidosInput(apellidos: string): Promise<void> {
    await this.apellidosInput.sendKeys(apellidos);
  }

  async getApellidosInput(): Promise<string> {
    return await this.apellidosInput.getAttribute('value');
  }

  async setEmailInput(email: string): Promise<void> {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput(): Promise<string> {
    return await this.emailInput.getAttribute('value');
  }

  async setUsuarioInput(usuario: string): Promise<void> {
    await this.usuarioInput.sendKeys(usuario);
  }

  async getUsuarioInput(): Promise<string> {
    return await this.usuarioInput.getAttribute('value');
  }

  async setPasswordInput(password: string): Promise<void> {
    await this.passwordInput.sendKeys(password);
  }

  async getPasswordInput(): Promise<string> {
    return await this.passwordInput.getAttribute('value');
  }

  async setFechaRegistroInput(fechaRegistro: string): Promise<void> {
    await this.fechaRegistroInput.sendKeys(fechaRegistro);
  }

  async getFechaRegistroInput(): Promise<string> {
    return await this.fechaRegistroInput.getAttribute('value');
  }

  async setCiudadInput(ciudad: string): Promise<void> {
    await this.ciudadInput.sendKeys(ciudad);
  }

  async getCiudadInput(): Promise<string> {
    return await this.ciudadInput.getAttribute('value');
  }

  async setUrlFotoPerfilInput(urlFotoPerfil: string): Promise<void> {
    await this.urlFotoPerfilInput.sendKeys(urlFotoPerfil);
  }

  async getUrlFotoPerfilInput(): Promise<string> {
    return await this.urlFotoPerfilInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class UsuariosDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-usuarios-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-usuarios'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
