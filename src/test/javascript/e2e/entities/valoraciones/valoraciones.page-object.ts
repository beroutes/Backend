import { element, by, ElementFinder } from 'protractor';

export class ValoracionesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-valoraciones div table .btn-danger'));
  title = element.all(by.css('jhi-valoraciones div h2#page-heading span')).first();
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

export class ValoracionesUpdatePage {
  pageTitle = element(by.id('jhi-valoraciones-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  estrellasInput = element(by.id('field_estrellas'));
  comentariosInput = element(by.id('field_comentarios'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setEstrellasInput(estrellas: string): Promise<void> {
    await this.estrellasInput.sendKeys(estrellas);
  }

  async getEstrellasInput(): Promise<string> {
    return await this.estrellasInput.getAttribute('value');
  }

  async setComentariosInput(comentarios: string): Promise<void> {
    await this.comentariosInput.sendKeys(comentarios);
  }

  async getComentariosInput(): Promise<string> {
    return await this.comentariosInput.getAttribute('value');
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

export class ValoracionesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-valoraciones-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-valoraciones'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
